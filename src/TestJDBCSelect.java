import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBCSelect {
    public static void main(String[] args) throws SQLException {
        // 1. 创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java102?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("111111");

        // 2. 和数据库建立连接
        Connection connection = dataSource.getConnection();

        // 3. 拼装sql
        String sql = "select * from student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4. 因为没有需要输入的信息，直接执行sql语句.对于查新操作，需要用executeQuery
        //    查询操作返回的不是一个 int 了， 而是一个 “临时表”
        //    使用 ResultSet 表示这个表
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5. 遍历结果集合（返回的临时表），先获取到每一行，再获取到这一行中的若干列
        //    next 方法表示获取到一行的记录，同时把光标往后移动一行
        //    如果遍历到表的结束位置，此处的 next 直接就返回 false 了
        /*
            +------+---------+
            | id   | name    |
            +------+---------+
            |    3 | Zhaoliu |
            +------+---------+
        */
        while (resultSet.next()) {
            // 针对当前这一行来获取到其中的列
            // 列名，从 1 开始数
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id = " + id +", name = " + name);
        }

        // 6. 释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
