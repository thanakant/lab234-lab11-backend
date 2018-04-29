package camt.se234.lab11.service;

import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    @Override
    public String getGrade(double score) {
        if (score > 79.5) {
            return "A";
        }
        else if (score > 74.5){
            return "B";
        }else if (score > 59.5){
            return "C";
        }else if (score > 32.5) {
            return "D";
        } else
            return "F";

    }


    @Override
    public String getGrade(double midtermScore, double finalScore) {
        String g ="";
        double sum = midtermScore+ finalScore;
        if (sum >= 80) {
            g= "A";
        } else if (sum >= 75 && sum < 80) {
            g= "B";
        } else if (sum >= 60 && sum < 75) {
            g= "C";
        } else if (sum >= 33 && sum < 60) {
            g= "D";
        }else if (sum < 33) {
            g="F";
        }
        return g;

    }
}
