package com.example.jsqlparserdemo;

import net.sf.jsqlparser.parser.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import org.junit.Test;

/**
 * @ClassName Demo2
 * @Description 实现为字段名和表明加上双引号
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-05 17:21
 */
public class Demo2 {
    @Test
    public void test10ParseVisitor() throws ParseException {
        String sql = "SELECT person.id,person.name,group.name FROM person JOIN group ON person.group_id = group.id WHERE person.birthdat > '1980-01-01'";
        /** 创建SQL语句解析器实例 */
        CCJSqlParser parser = CCJSqlParserUtil.newParser(sql);
        /** 解析SQL语句 */
        Statement stmt = parser.Statement();
        /** 使用 LogVisiter对象遍历AST的所有节点 */
        parser.getASTRoot().jjtAccept(new LogVisitor(), null);

        System.out.println(stmt);
    }

    /**
     * 遍历所有节点的{@link net.sf.jsqlparser.parser.CCJSqlParserVisitor} 接口实现类
     *
     * @author guyadong
     */
    static class LogVisitor extends CCJSqlParserDefaultVisitor {
        @Override
        public Object visit(SimpleNode node, Object data) {
            Object value = node.jjtGetValue();
            /** 根据节点类型找出表名和字段名节点，对名字加上双引号 */
            if (node.getId() == CCJSqlParserTreeConstants.JJTCOLUMN) {
                System.out.println(value.toString());
                Column column = (Column) value;
                column.setColumnName("\"" + column.getColumnName() + "\"");
                Table table = column.getTable();
                if (null != table) {
                    table.setName("\"" + table.getName() + "\"");
                }
            } else if (node.getId() == CCJSqlParserTreeConstants.JJTTABLE) {
                System.out.println(value.toString());
                Table table = (Table) value;
                table.setName("\"" + table.getName() + "\"");
            } else if (null != value) {
                /** 其他类型节点输出节点类型值，Java类型和节点值 */
                System.out.println(node.getId() + ", " + value.getClass().getSimpleName() + ", " + value);
            }
            return super.visit(node, data);
        }
    }
}
