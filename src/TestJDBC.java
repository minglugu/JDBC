import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
// this connection is for JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        // low coupling
        // 向上转型 upcasting
        // 1. 创建好数据源
        DataSource dataSource = new MysqlDataSource();

        // 向下转型 downcasting
        // set the address of databse, 设置数据库所在的地址
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java102?characterEncoding=utf8&useSSL=false");
        // set user name to login the db
        ((MysqlDataSource) dataSource).setUser("root");
        // set password to login the db
        ((MysqlDataSource) dataSource).setPassword("111111");

        // 2. 让代码和数据库服务器建立连接, 用 throws 来处理异常，到达快递站
        Connection connection = dataSource.getConnection();

        // 2.5 让用户通过控制台，输入一下，待插入的数据

        System.out.println("请输入学号：");
        int id = scanner.nextInt();
        System.out.println("请输入姓名：");
        String name = scanner.next();

        // 3. 操作数据库，例如插入数据
        // 构造一个 SQL 语句
        // 在 JDBC 中构造的 SQL，不需要加上“；”
        // “；” 只是在命令行中，用来区分不同的语句，现在是直接在代码中操作
        // 如果用字符串拼接的方法，容易引起”sql注入攻击“。黑客攻击服务器的一种手段
        String sql = "insert into student values(?, ?)"; // 告诉java 程序，这两个字段的值，还不确定，用？ 先给占个位子
        // 此处光是一个 String 类型的 sql 还不行，需要把这个 String 包装成一个 “语句对象”
        // 把字符串的sql 转成了一个 JDBC 的对象
        PreparedStatement statement = connection.prepareStatement(sql);
        // 用setInt和setString来进行替换 ? 的操作. index starts from 1
        statement.setInt(1, id);
        statement.setString(2, name);
        System.out.println("Statement " + statement);

        // 4. 执行 SQL，相当于扫码取件
        // SQL 里面如果是 insert, update, delete, 都使用 executeUpdate 方法
        // SQL 里面如果是 select, 则使用 executeQuery 方法
        // 返回值就表示这个操作，影响到了几行，就相当于在控制台里，输入 sql 之后，得到的数字. 比如说 "0 rows affected"
        int ret = statement.executeUpdate();
        System.out.println(ret);

        // 5. 此时 SQL 已经执行完毕，然后还需要释放资源。
        // 释放资源的时候，先释放 statement，再释放 statement。申请的顺序和释放的顺序，是相反的。
        statement.close();
        connection.close();
    }
}
