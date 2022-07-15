package app.board;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// printArticles();
		// printArticleComments();
	}

	private void printArticles() {
		// ('제목 1', '본문 1', 'java', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),
		for (int i = 1; i <= 50; i++) {
			String s = "";
			if (i <= 10) { // 1 ~ 10
				s = String.format("('제목 %d', '본문 %d', 'java', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i, i);
			}
			if (i > 10 && i <= 20) {
				s = String.format("('제목 %d', '본문 %d', 'java', 'tester', 'tester', '2022-07-15 02:00:00', '2022-07-15 02:00:00'),", i, i);
			}
			if (i > 20 && i <= 30) {
				s = String.format("('제목 %d', '본문 %d', 'java', 'tester', 'tester', '2022-07-15 03:00:00', '2022-07-15 03:00:00'),", i, i);
			}
			if (i > 30 && i <= 40) {
				s = String.format("('제목 %d', '본문 %d', 'java', 'tester', 'tester', '2022-07-15 04:00:00', '2022-07-15 04:00:00'),", i, i);
			}
			if (i > 40) {
				s = String.format("('제목 %d', '본문 %d', 'java', 'tester', 'tester', '2022-07-15 05:00:00', '2022-07-15 05:00:00'),", i, i);
			}

			System.out.println(s);
		}
	}

	private void printArticleComments() {
		// (1, '본문 1', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),
		for (int i = 1; i <= 50; i++) {
			String s = "";
			if (i <= 10) {
				s = String.format("(50, '본문 %d', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i);
			}
			if (i > 10 && i <= 20) {
				s = String.format("(49, '본문 %d', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i);
			}
			if (i > 20 && i <= 30) {
				s = String.format("(48, '본문 %d', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i);
			}
			if (i > 30 && i <= 40) {
				s = String.format("(47, '본문 %d', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i);
			}
			if (i > 40) {
				s = String.format("(46, '본문 %d', 'tester', 'tester', '2022-07-15 01:00:00', '2022-07-15 01:00:00'),", i);
			}

			System.out.println(s);
		}
	}
}