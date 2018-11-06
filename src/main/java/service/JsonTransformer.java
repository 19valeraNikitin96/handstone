package service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonTransformer {
    private Gson gson = new Gson();

    public String toJson(Object o) {
        return gson.toJson(o);
    }
}
