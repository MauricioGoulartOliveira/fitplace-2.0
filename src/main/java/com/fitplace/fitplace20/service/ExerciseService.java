package com.fitplace.fitplace20.service;


import com.fitplace.fitplace20.model.Exercise;
import com.fitplace.fitplace20.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.setName(updatedExercise.getName());
                    exercise.setDescription(updatedExercise.getDescription());
                    exercise.setType(updatedExercise.getType());
                    exercise.setDuration(updatedExercise.getDuration());
                    return exerciseRepository.save(exercise);
                })
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
