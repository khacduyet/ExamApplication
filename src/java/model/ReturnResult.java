/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Contest;
import entities.Exam;
import entities.Subject;
import entities.Users;

/**
 *
 * @author Admin
 */
public class ReturnResult {

    private double point;
    private Exam exam;
    private Users user;
    private Contest contest;
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    private int minuted;
    private int corrected;
    private double score;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public int getMinuted() {
        return minuted;
    }

    public void setMinuted(int minuted) {
        this.minuted = minuted;
    }

    public int getCorrected() {
        return corrected;
    }

    public void setCorrected(int corrected) {
        this.corrected = corrected;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
