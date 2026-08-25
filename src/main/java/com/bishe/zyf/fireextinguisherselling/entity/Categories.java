package com.bishe.zyf.fireextinguisherselling.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 分类表
 * @TableName categories
 */
@TableName(value ="categories")
@Data
public class Categories {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 灭火器类型
     */
    private String name;

    /**
     * 检测周期，单位：年
     */
    private Integer testCycle;

    /**
     * 报废年限，单位：年
     */
    private Integer serviceLifeYears;

    /**
     * 是否启用 0否 1是
     */
    private Integer isActive;

    /**
     * 首次维修以后的检测周期，单位：年
     */
    private Integer afterFixTestCycle;

    /**
     * 巡检周期
     */
    private String inspectionCycle;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Categories other = (Categories) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTestCycle() == null ? other.getTestCycle() == null : this.getTestCycle().equals(other.getTestCycle()))
                && (this.getServiceLifeYears() == null ? other.getServiceLifeYears() == null : this.getServiceLifeYears().equals(other.getServiceLifeYears()))
                && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
                && (this.getAfterFixTestCycle() == null ? other.getAfterFixTestCycle() == null : this.getAfterFixTestCycle().equals(other.getAfterFixTestCycle()))
                && (this.getInspectionCycle() == null ? other.getInspectionCycle() == null : this.getInspectionCycle().equals(other.getInspectionCycle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTestCycle() == null) ? 0 : getTestCycle().hashCode());
        result = prime * result + ((getServiceLifeYears() == null) ? 0 : getServiceLifeYears().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getAfterFixTestCycle() == null) ? 0 : getAfterFixTestCycle().hashCode());
        result = prime * result + ((getInspectionCycle() == null) ? 0 : getInspectionCycle().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", testCycle=").append(testCycle);
        sb.append(", serviceLifeYears=").append(serviceLifeYears);
        sb.append(", isActive=").append(isActive);
        sb.append(", afterFixTestCycle=").append(afterFixTestCycle);
        sb.append(", inspectionCycle=").append(inspectionCycle);
        sb.append("]");
        return sb.toString();
    }
}