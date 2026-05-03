package models;

public class Parrot extends Bird {
    private String vocabularySize;

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, String vocabularySize){
        super(name, age, owner, id, wingSpan, canFly);
        setVocabularySize(vocabularySize);
    }
    public String getVocabularySize(){
        return vocabularySize;
    }
    public void setVocabularySize(String vocabularySize){
        if (vocabularySize == null || vocabularySize.isEmpty()){
            this.vocabularySize = "Amazing";
        }else{
            this.vocabularySize = vocabularySize;
        }
    }
    @Override
    public double calculateWeeklyFee(){
        return 10 * 7;
    }
    @Override
    public String toString(){
        return super.toString() + ", Vocabulary Size: " + vocabularySize;
    }
}
