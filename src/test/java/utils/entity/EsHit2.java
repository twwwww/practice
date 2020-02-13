package utils.entity;

public class EsHit2 {
    private String _id;
    private Float _score;
    private HujingChatRecord _source;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Float get_score() {
        return _score;
    }

    public void set_score(Float _score) {
        this._score = _score;
    }

    public HujingChatRecord get_source() {
        return _source;
    }

    public void set_source(HujingChatRecord _source) {
        this._source = _source;
    }
}
