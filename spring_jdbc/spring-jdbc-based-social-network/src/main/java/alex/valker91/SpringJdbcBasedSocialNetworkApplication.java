package alex.valker91;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SpringJdbcBasedSocialNetworkApplication implements CommandLineRunner {

    private final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcBasedSocialNetworkApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) {
        dropTables();

        createTables();
        generateUsers(1_000);
        generateFriendships(70_000, 1_000);
        generatePosts(50_000, 1_000);
        generateLikes(300_000, 1_000, 50_000);

        List<String> users = findUsers();
        System.out.println(users.size());
    }

    private void createTables() {
        // table 'Users'
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS Users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(50),
                surname VARCHAR(50),
                birthdate DATE
            )""");

        // table 'Friendships'
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS Friendships (
                userid1 INT REFERENCES Users(id),
                userid2 INT REFERENCES Users(id),
                timestamp TIMESTAMP,
                PRIMARY KEY (userid1, userid2)
            )""");

        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS Posts (
                id BIGSERIAL PRIMARY KEY,
                userId INT REFERENCES Users(id),
                text TEXT,
                timestamp TIMESTAMP
            )""");

        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS Likes (
                postid INT REFERENCES Posts(id),
                userid INT REFERENCES Users(id),
                timestamp TIMESTAMP,
                PRIMARY KEY (postid, userid)
            )""");
    }

    private void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS Likes CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Posts CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Friendships CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users CASCADE");
    }

    private void generateUsers(int count) {
        for (int i = 0; i < count; i++) {
            String name = "Alex_"+i;
            String surname = "Valker_"+i;
            Date birthdate = new Date();

            jdbcTemplate.update(
                    "INSERT INTO Users (name, surname, birthdate) VALUES (?, ?, ?)",
                    name, surname, birthdate
            );
        }
    }

    private void generateFriendships(int count, int usersCount) {
        for (int i = 0; i < count; i++) {
            int userId1 = random.nextInt(usersCount) + 1;
            int userId2 = random.nextInt(usersCount) + 1;

            if (userId1 == userId2) {
                i--;
                continue;
            }
            Date date = new Date();

            try {
                jdbcTemplate.update(
                        "INSERT INTO Friendships (userid1, userid2, timestamp) VALUES (?, ?, ?)",
                        userId1, userId2, date
                );
            } catch (Exception e) {
                i--;
            }
        }
    }

    private void generatePosts(int count, int usersCount) {
        for (int i = 0; i < count; i++) {
            int userId = random.nextInt(usersCount) + 1;
            String text = "text_" + i;
            Date date = new Date();

            jdbcTemplate.update(
                    "INSERT INTO Posts (userId, text, timestamp) VALUES (?, ?, ?)",
                    userId, text, date
            );
        }
    }

    private void generateLikes(int count, int usersCount, int postsCount) {
        LocalDateTime march2025 = LocalDateTime.of(2025, 3, 25, 0, 0);
        for (int i = 0; i < count; i++) {
            int postId = random.nextInt(postsCount) + 1;
            int userId = random.nextInt(usersCount) + 1;
            try {
                jdbcTemplate.update(
                        "INSERT INTO Likes (postid, userid, timestamp) VALUES (?, ?, ?)",
                        postId, userId, march2025
                );
            } catch (Exception e) {
                i--;
            }
        }
    }

    private List<String> findUsers() {
        return jdbcTemplate.queryForList("""
            SELECT DISTINCT u.name 
            FROM Users u
            WHERE (
                SELECT COUNT(*) FROM Friendships f 
                WHERE f.userid1 = u.id OR f.userid2 = u.id
            ) > 100
            AND 
            (
                SELECT COUNT(*) FROM Likes l 
                WHERE l.userid = u.id 
                AND l.timestamp >= '2025-03-01' 
                AND l.timestamp < '2025-04-01'
            ) > 100
            """, String.class);
    }
}
