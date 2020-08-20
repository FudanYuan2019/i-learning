package builder;

/**
 * @Author: Jeremy
 * @Date: 2020/8/20 17:19
 */
public class TestMain {
    public static void main(String[] args) {
        QueryCondition queryCondition1 = new QueryCondition.QueryConditionBuilder()
                .username("test")
                .pageNo(1)
                .pageSize(10)
                .fuzzy(true)
                .useCategory(false)
                .keyword("Adidas")
                .build();
        System.out.println(queryCondition1.toString());

        QueryCondition queryCondition2 = new QueryCondition.QueryConditionBuilder()
                .username("Jeremy")
                .pageNo(1)
                .pageSize(10)
                .fuzzy(false)
                .useCategory(true)
                .category("shoes")
                .keyword("Adidas")
                .build();
        System.out.println(queryCondition2.toString());

    }
}
