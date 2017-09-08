package com.p.wd.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wordscollection")
public class Wordi {	

	// public enum Type {
	// adjective, verb, noun, adverb;
	// }

	@Id
	private String id;
	private String word;
	private String type;

	private List<Meaningi> meanings = new ArrayList<Meaningi>();

	private List<Examplei> examples = new ArrayList<Examplei>();
	
	public Wordi(){}

	public Wordi(String id, String word, String type) {
		super();
		this.id = id;
		this.word = word;
		this.type = type;
	}

	public void addMeaning(String meaning) {
		if (meaning == null || meaning.trim().equals("")) {
			return;
		}
		meanings.add(new Meaningi(meanings.size() + 1, meaning));
	}

	public void addExample(String example) {
		if (example == null || example.trim().equals("")) {
			return;
		}
		examples.add(new Examplei(examples.size() + 1, example));
	}

	public String getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public String getType() {
		return type;
	}

	public List<Meaningi> getMeanings() {
		return Collections.unmodifiableList(meanings);
	}

	public List<Examplei> getExamples() {

		return Collections.unmodifiableList(examples);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word [id=");
		builder.append(id);
		builder.append(", word=");
		builder.append(word);
		builder.append(", type=");
		builder.append(type);
		builder.append(", meanings=");
		builder.append(meanings);
		builder.append(", examples=");
		builder.append(examples);
		builder.append("]");
		return builder.toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMeanings(List<Meaningi> meanings) {
		this.meanings = meanings;
	}

	public void setExamples(List<Examplei> examples) {
		this.examples = examples;
	}

}
