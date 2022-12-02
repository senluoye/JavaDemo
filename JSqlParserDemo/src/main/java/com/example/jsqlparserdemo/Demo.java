package com.example.jsqlparserdemo;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-02 17:17
 */
public class Demo {

    public static void main(String[] args) throws JSQLParserException {
        String sql = "select a.id, a.name, b.age from user a, user2 b where a.id = b.id";
        List<String> strings = testSelectItems(sql);
        System.out.println(strings);
    }

    private static List<String> testSelectItems(String sql) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) CCJSqlParserUtil.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectitems = plain.getSelectItems();
        List<String> str_items = new ArrayList<>();
        if (selectitems != null) {
            for (SelectItem selectitem : selectitems) {
                str_items.add(selectitem.toString());
            }
        }
        return str_items;
    }

    private static List<String> test_select_join(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;
        PlainSelect plain = (PlainSelect) selectStatement.getSelectBody();
        List<Join> joinList = plain.getJoins();
        List<String> tablewithjoin = new ArrayList<String>();
        if (joinList != null) {
            for (Join join : joinList) {
                join.setLeft(false);
                tablewithjoin.add(join.toString());
                //注意 ， leftjoin rightjoin 等等的to string()区别
            }
        }
        return tablewithjoin;
    }
}
