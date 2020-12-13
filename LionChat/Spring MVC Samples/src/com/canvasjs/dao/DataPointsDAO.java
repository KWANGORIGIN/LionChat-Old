package com.canvasjs.dao;

import java.util.List;
import com.canvasjs.model.DataPointsModel;
import javax.sql.DataSource;

public interface DataPointsDAO 
{
    public List<DataPointsModel> getDataPoints();
    public void setDataSource(DataSource ds);
}