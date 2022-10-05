import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestJDBCDelete {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // 删除数据库中的记录
        // 让用户输入一个 id，根据 id 来删除
        // 1. 创建数据源
        DataSource dataSource = new MysqlDataSource();

        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java102?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("111111");

        // 2. 建立连接
        Connection connection = dataSource.getConnection();

        // 3. input from user
        System.out.println("请输入一个要删除的id");
        int id = scanner.nextInt();

        // 4. 拼装 sql 语句
        String sql = "delete from student where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        // 5. 执行sql
        int ret = preparedStatement.executeUpdate();
        System.out.println("ret = " + ret);

        // 6. 回收和释放资源
        preparedStatement.close();
        connection.close();
    }
}
