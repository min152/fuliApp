package project.first.lal.moudle.fanhao;

public class DesignationModel {
    private Integer id;

    private Integer hotId;

    private String actress;

    private String fanhao;

    private String link;

    private String localPath;

    private String source;

    private Integer type;

    private String label;

    private Float score;

    private String releaseTime;

    private String filmDistributor;
    
    private String orderIndex;//排序字段
    
    private String groupIndex;//分组字段
    
    private String descType;//正序 倒序
    
    public String getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(String groupIndex) {
		this.groupIndex = groupIndex;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getDescType() {
		return descType;
	}

	public void setDescType(String descType) {
		this.descType = descType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotId() {
        return hotId;
    }

    public void setHotId(Integer hotId) {
        this.hotId = hotId;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress == null ? null : actress.trim();
    }

    public String getFanhao() {
        return fanhao;
    }

    public void setFanhao(String fanhao) {
        this.fanhao = fanhao == null ? null : fanhao.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath == null ? null : localPath.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime == null ? null : releaseTime.trim();
    }

    public String getFilmDistributor() {
        return filmDistributor;
    }

    public void setFilmDistributor(String filmDistributor) {
        this.filmDistributor = filmDistributor == null ? null : filmDistributor.trim();
    }
}