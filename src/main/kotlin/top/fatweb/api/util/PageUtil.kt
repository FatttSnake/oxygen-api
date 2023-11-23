package top.fatweb.api.util

import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import top.fatweb.api.param.PageSortParam

/**
 * Page util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object PageUtil {
    fun <T: Page<*>> setPageSort(pageSortParam: PageSortParam?, page: T, defaultOrder: OrderItem? = null) {
        if (pageSortParam?.sortField != null || pageSortParam?.sortOrder != null) {
            page.addOrder(
                if (pageSortParam.sortOrder?.startsWith(
                        "desc", true
                    ) == true
                ) OrderItem.desc(StrUtil.upperToUnderLetter(pageSortParam.sortField)) else OrderItem.asc(
                    StrUtil.upperToUnderLetter(
                        pageSortParam.sortField
                    )
                )
            )
        } else {
            defaultOrder?.let { page.addOrder(defaultOrder) }
        }
    }
}