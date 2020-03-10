package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PlaceModel implements Serializable {
    private String id;

    private String mainlandname;

    private String usephone;

    private String modelid;

    private String plotname;

    private Integer num;

    private Integer heat;

    private Integer x;

    private Integer y;

    private Integer z;

    private Date createtime;

    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMainlandname() {
        return mainlandname;
    }

    public void setMainlandname(String mainlandname) {
        this.mainlandname = mainlandname == null ? null : mainlandname.trim();
    }

    public String getUsephone() {
        return usephone;
    }

    public void setUsephone(String usephone) {
        this.usephone = usephone == null ? null : usephone.trim();
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid == null ? null : modelid.trim();
    }

    public String getPlotname() {
        return plotname;
    }

    public void setPlotname(String plotname) {
        this.plotname = plotname == null ? null : plotname.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mainlandname=").append(mainlandname);
        sb.append(", usephone=").append(usephone);
        sb.append(", modelid=").append(modelid);
        sb.append(", plotname=").append(plotname);
        sb.append(", num=").append(num);
        sb.append(", heat=").append(heat);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", z=").append(z);
        sb.append(", createtime=").append(createtime);
        sb.append(", price=").append(price);
        sb.append("]");
        return sb.toString();
    }
}