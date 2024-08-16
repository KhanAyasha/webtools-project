/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;

/**
 *
 * @author ayashakhan
 */

@Entity
@Table(name = "Courses")
@NamedQueries({
        @NamedQuery(name = "selectBycourseId", query = "FROM Courses WHERE courseId = :courseId")
})
public class Courses {
    
        @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="courseId")
	private int courseId;
	
	@Column(name = "courseName")
	private String courseName;
	
	@Column(name = "description")
	private String description;
        
        @ManyToOne
        @JoinColumn(name="contributorID", referencedColumnName = "contributorID", nullable = false)
        private Contributor contributor;
        
//        // One-to-Many relationship with Course
//        @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
//        private List<Contributor> contributors;
//        
//	
	public Courses() {
		
	}
	
	public Courses(int courseId, String courseName, String description, Contributor contributor) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.description = description;
                this.contributor = contributor;
	}

        
        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Contributor getContributor() {
            return contributor;
        }

        public void setContributor(Contributor contributor) {
            this.contributor = contributor;
        }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
        

	
}
