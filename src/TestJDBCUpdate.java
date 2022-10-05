import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestJDBCUpdate {
    public static void main(String[] args) throws SQLException {
        // 根据 id 来修改学生姓名，让用户输入 要修改的 id，以及对应的修改后的名字。
        // 1. 创建数据源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java102?characterEncoding=utf8&useSSL=false"); // database URL
        ((MysqlDataSource) dataSource).setUser("root"); // database username, default is root
        ((MysqlDataSource) dataSource).setPassword("111111"); // database password

        // 2. 和数据库建立连接
        Connection connection = dataSource.getConnection();

        // 3. 输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个要更新的id：");
        int id = scanner.nextInt();
        System.out.println("请输入要修改的学生姓名：");
        String name = scanner.next();

        // 4. 拼装 sql
        String sql = "update student set name = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        System.out.println("Statement: " + preparedStatement);

        // 5. 执行 sql
        int ret = preparedStatement.executeUpdate();
        System.out.println("ret = "+ ret);

        // 6. 回收资源
        preparedStatement.close();
        connection.close();
    }
}
