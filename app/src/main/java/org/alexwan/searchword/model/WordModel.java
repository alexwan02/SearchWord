package org.alexwan.searchword.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Word
 * Created by alexwan on 16/6/14.
 */
public class WordModel extends RealmObject{
    @PrimaryKey
    private String content;
    private int id;
    private int conent_id;                     //
    private String pronunciation;              // pronunciation
    private WordPronunciation  pronunciations; // uk & us pronunciation
    private String definition;                 // chinese definition
    private String audio_name;                 // the name of audio
    private String uk_audio;                   // uk audio address
    private String us_audio;                   // us audio address
    private boolean has_audio;                 // has audio resource or not
    private WordDefinitionModel cn_definition; // chinese definition of word
    private WordDefinitionModel en_definition; // english definition of word
    private long date = System.currentTimeMillis();
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WordPronunciation getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(WordPronunciation pronunciations) {
        this.pronunciations = pronunciations;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getConentId() {
        return conent_id;
    }

    public void setConentId(int conent_id) {
        this.conent_id = conent_id;
    }

    public String getAudioName() {
        return audio_name;
    }

    public void setAudioName(String audio_name) {
        this.audio_name = audio_name;
    }

    public String getUkAudio() {
        return uk_audio;
    }

    public void setUkAudio(String uk_audio) {
        this.uk_audio = uk_audio;
    }

    public String getUsAudio() {
        return us_audio;
    }

    public void setUsAudio(String us_audio) {
        this.us_audio = us_audio;
    }

    public boolean isHasAudio() {
        return has_audio;
    }

    public void setHasAudio(boolean has_audio) {
        this.has_audio = has_audio;
    }

    public WordDefinitionModel getCnDefinition() {
        return cn_definition;
    }

    public void setCnDefinition(WordDefinitionModel cn_definition) {
        this.cn_definition = cn_definition;
    }

    public WordDefinitionModel getEnDefinition() {
        return en_definition;
    }

    public void setEnDefinition(WordDefinitionModel en_definition) {
        this.en_definition = en_definition;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
