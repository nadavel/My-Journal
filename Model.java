package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Model {
	
	private ArrayList<Question> questions;
	private ResultSet rs;
	private int index;
	
	public Model() {
		index = 0;
		questions = new ArrayList<>();
		try {
			rs = DB.getQuestions();
			while (rs.next()) {
				int id = rs.getInt("id");
				String primary = rs.getString("primary");
				String related = rs.getString("related");
				String followup = rs.getString("followup");
				questions.add(new Question(id, primary, related, followup));
			}
			Collections.shuffle(questions);
		} catch (SQLException e) {
			Alert.display(e.getMessage());
		}
	}
	
	public Question getQuestion() {
		Question q = questions.get(index);
		index = (index+1)%questions.size();
		return q;
	}
}
