package top.fatweb.oxygen.api.config

import org.springframework.aot.generate.GenerationContext
import org.springframework.beans.factory.aot.*
import org.springframework.beans.factory.support.RegisteredBean
import org.springframework.javapoet.CodeBlock
import org.springframework.javapoet.MethodSpec
import javax.lang.model.element.Modifier

/**
 * Replaces the [ResourceProviderCustomizer] bean with a
 * [NativeImageResourceProviderCustomizer] bean.
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
class ResourceProviderCustomizerBeanRegistrationAotProcessor : BeanRegistrationAotProcessor {
    override fun processAheadOfTime(registeredBean: RegisteredBean): BeanRegistrationAotContribution? {
        if (registeredBean.beanClass == ResourceProviderCustomizer::class.java) {
            return BeanRegistrationAotContribution
                .withCustomCodeFragments { codeFragments: BeanRegistrationCodeFragments ->
                    AotContribution(
                        codeFragments,
                        registeredBean
                    )
                }
        }
        return null
    }

    private class AotContribution(
        delegate: BeanRegistrationCodeFragments,
        private val registeredBean: RegisteredBean
    ) : BeanRegistrationCodeFragmentsDecorator(delegate) {
        override fun generateInstanceSupplierCode(
            generationContext: GenerationContext,
            beanRegistrationCode: BeanRegistrationCode, allowDirectSupplierShortcut: Boolean
        ): CodeBlock {
            val generatedMethod =
                beanRegistrationCode.methods.add("getInstance") { method: MethodSpec.Builder? ->
                    method!!.addJavadoc($$"Get the bean instance for '$L'.", this.registeredBean.beanName)
                    method.addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    method.returns(NativeImageResourceProviderCustomizer::class.java)
                    val code = CodeBlock.builder()
                    code.addStatement($$"return new $T()", NativeImageResourceProviderCustomizer::class.java)
                    method.addCode(code.build())
                }
            return generatedMethod.toMethodReference().toCodeBlock()
        }
    }
}
