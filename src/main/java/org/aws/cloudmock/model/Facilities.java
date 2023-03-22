package org.aws.cloudmock.model;

public class Facilities {

    private Long facilityId;
    private Integer totalCapacity;
    private Integer currentOccupancy;
    private Integer numWheelchairAvailable;

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(Integer currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public Integer getNumWheelchairAvailable() {
        return numWheelchairAvailable;
    }

    public void setNumWheelchairAvailable(Integer numWheelchairAvailable) {
        this.numWheelchairAvailable = numWheelchairAvailable;
    }
}
