package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

/**
 * @author Devin Peevy
 */
public class Testimonial {
    //test push

    public final static String SEATTLE = "Seattle";

    public final static String TACOMA = "Tacoma";

    public final static String BOTHELL = "Bothell";

    public final static String SPRING = "Spring";

    public final static String SUMMER = "Summer";

    public final static String FALL = "Fall";

    public final static String WINTER = "Winter";

    private final int studentID;

    private final String studentName;

    private final String studentCampus;

    private final String programSeason;

    private String testimonialTitle;

    private String testimonialContent;

    public Testimonial(int studentID,
                       String studentName,
                       String studentCampus,
                       String programSeason,
                       int programYear) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCampus = studentCampus;
        this.programSeason = programSeason + " " + programYear;
        this.testimonialTitle = null;
        this.testimonialContent = null;
    }

    public void addTestimonial(String testimonialTitle,
                               String testimonialContent) {
        this.testimonialTitle = testimonialTitle;
        this.testimonialContent = testimonialContent;
    }

}

