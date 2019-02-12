package ftn.upp.sc.model.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = IndexUnit.INDEX_NAME, type = IndexUnit.TYPE_NAME, shards = 1, replicas = 0)
public class IndexUnit {

	public static final String INDEX_NAME = "digitallibrary";
	public static final String TYPE_NAME = "paper";
	public static final String ANALYZER_NAME = "serbian-analyzer";
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String magazineName;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String text;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String title;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String authorName;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String keywords;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String abstractText;
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String scienceField;
	@Id
	@Field(type = FieldType.Text, store = true, analyzer = ANALYZER_NAME)
	private String filename;
	
	public IndexUnit() {
		
	}
	
	public IndexUnit(String magazineName, String text, String title,String authorName, String keywords, String abstractText, String scienceField,
			String filename) {
		super();
		this.authorName = authorName;
		this.magazineName = magazineName;
		this.text = text;
		this.title = title;
		this.keywords = keywords;
		this.abstractText = abstractText;
		this.scienceField = scienceField;
		this.filename = filename;
	}
	
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getScienceField() {
		return scienceField;
	}
	public void setScienceField(String scienceField) {
		this.scienceField = scienceField;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "IndexUnit [magazineName=" + magazineName + ", text=" + text + ", title=" + title + ", authorName="
				+ authorName + ", keywords=" + keywords + ", abstractText=" + abstractText + ", scienceField="
				+ scienceField + ", filename=" + filename + "]";
	}
	
	
	
}