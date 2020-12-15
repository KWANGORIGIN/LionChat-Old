package psu.lionchat.entity.entities;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import psu.lionchat.entity.Entity;

public class DateTimeEntity extends Entity {
	private final Calendar dateTime;

	public DateTimeEntity()
	{
		this.dateTime = new GregorianCalendar();
		this.entityName = "dateTime";
		this.prompt = "Please enter the date and time of interest in MM-dd-yyyy format or type \"today\" to " +
				"get today's events:";
	}
	@Override
	public String getEntityInformation() {
		String timeString = String.format("%02d", this.dateTime.get(Calendar.MONTH) + 1)  + "-" +
				String.format("%02d", this.dateTime.get(Calendar.DAY_OF_MONTH)) + "-" +
				String.format("%02d", this.dateTime.get(Calendar.YEAR)) + " " +
				String.format("%02d", this.dateTime.get(Calendar.HOUR)) + ":" +
				String.format("%02d", this.dateTime.get(Calendar.MINUTE)) + ":" +
				String.format("%02d", this.dateTime.get(Calendar.SECOND)) + " ";

		if(this.dateTime.get(Calendar.AM_PM) == 1)
		{
			timeString += "PM";
		}
		else
		{
			timeString += "AM";
		}

		return timeString;
	}

	/**
	 * Convert the date stored in this entity to a database readable format.
	 * */
	public Timestamp getTimestamp()
	{
		return new Timestamp(this.dateTime.getTimeInMillis());
	}

	@Override
	public boolean setEntityInformation(String info) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");

		try
		{
			Date infoDate = dateFormatter.parse(info, new ParsePosition(0));
			this.dateTime.setTime(infoDate);
			this.hasInfo = true;
			return this.hasInfo;
		}
		catch(NullPointerException e)
		{
			if(!((info).equalsIgnoreCase("today")))
			{
				System.err.println("Invalid date format.");
			}
			else
			{
				this.hasInfo = true;
			}

			return this.hasInfo;
		}
	}

}