package top.fatweb.oxygen.api.util

import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Set sort parameters into page object
 *
 * @param pageSortParam Sort parameters
 * @param page Page object
 * @param defaultOrder Default order by
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see Page
 * @see PageSortParam
 * @see OrderItem
 */
fun <T : Page<*>> setPageSort(pageSortParam: PageSortParam?, page: T, defaultOrder: OrderItem? = null) {
    if (pageSortParam?.sortField != null || pageSortParam?.sortOrder != null) {
        page.addOrder(
            if (pageSortParam.sortOrder?.startsWith(
                    "desc", true
                ) == true
            ) OrderItem.desc(upperToUnderLetter(pageSortParam.sortField)) else OrderItem.asc(
                upperToUnderLetter(
                    pageSortParam.sortField
                )
            )
        )
    } else {
        defaultOrder?.let { page.addOrder(defaultOrder) }
    }
}
