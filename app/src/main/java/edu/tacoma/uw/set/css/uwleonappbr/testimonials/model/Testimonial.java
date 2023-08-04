package edu.tacoma.uw.set.css.uwleonappbr.testimonials.model;

/**
 * This is the object class that holds all the information included in a testimonial submitted by
 * the student. Each field corresponds to a column in the Testimonials table in mysql database.
 * @author Devin Peevy
 */
public class Testimonial {

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

    private final String studentMajor;

    private final String programQuarter;

    private String testimonialTitle;

    private String testimonialContent;



    private int imageStudent;

    /**
     * This is how a Testimonial is going to be constructed by the SubmitStudentInfoFragment.
     * @param studentID 7 digit UW student id number.
     * @param studentName The first and last name of the student.
     * @param studentCampus The UW campus the student normally attends: SEATTLE, TACOMA, or BOTHELL.
     * @param studentMajor The student's UW major.
     * @param programSeason The season of the student's program: SPRING, SUMMER, FALL, or WINTER.
     * @param programYear 4 digit year of the student's program.
     */
    public Testimonial(int studentID,
                       String studentName,
                       String studentCampus,
                       String studentMajor,
                       String programSeason,
                       int programYear,int imageStudent) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentCampus = studentCampus;
        this.studentMajor = studentMajor;
        this.programQuarter = programSeason + " " + programYear;
        this.imageStudent = imageStudent;
        this.testimonialTitle = null;
        this.testimonialContent = null;
    }

    /**
     * The SubmitTestimonialFragment will then complete construction using this method.
     * @param testimonialTitle A short sentence/phrase to describe the student's experience MAX: 50.
     * @param testimonialContent The content of the testimonial MAX: 1,000.
     */
    public void addTestimonial(String testimonialTitle,
                               String testimonialContent) {
        this.testimonialTitle = testimonialTitle;
        this.testimonialContent = testimonialContent;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentCampus() {
        return studentCampus;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public String getProgramQuarter() {
        return programQuarter;
    }

    public String getTestimonialTitle() {
        return testimonialTitle;
    }

    public String getTestimonialContent() {
        return testimonialContent;
    }

    public int getImageStudent() {
        return imageStudent;
    }
}

