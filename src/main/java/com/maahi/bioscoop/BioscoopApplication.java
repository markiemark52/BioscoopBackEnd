package com.maahi.bioscoop;

import com.maahi.bioscoop.entities.Auditorium;
import com.maahi.bioscoop.entities.Film;
import com.maahi.bioscoop.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BioscoopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioscoopApplication.class, args);

		hibernate();
	}

	private static void hibernate() {
		User user = new User("admin@bioscoop.nl", "$2y$12$5lj3b5BDpe93fmilmtLsyesuYzzjVDIUfovRnX2skfLxDb9NIVfum", "Admin", "Admin", true);
		Film film1 = new Film("Film One", "The First Film");
		film1.setUser(user);
		Film film2 = new Film("Film Two", "The Second Film");
		film2.setUser(user);

		Auditorium auditorium1 = new Auditorium("Auditorium 1", "The First Auditorium");
		Auditorium auditorium2 = new Auditorium("Auditorium 2", "The Second Auditorium");

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.save(user);
			session.save(film1);
			session.save(film2);
			session.save(auditorium1);
			session.save(auditorium2);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Film> films = session.createQuery("from Film", Film.class).list();
			films.forEach(f -> System.out.println(f.getTitle()));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
