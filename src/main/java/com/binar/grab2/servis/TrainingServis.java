package com.binar.grab2.servis;
import com.binar.grab2.models.Training;
import java.util.Map;
public interface TrainingServis {
    public Map insert(Training obj);

    public Map update(Training obj);

    public Map delete(Long idTraining);

    public Map getAll(Integer size, Integer page);
}
