package controllers;

import java.util.Date;
import java.util.List;
import models.Log;
import play.libs.Json;
import play.mvc.*;

/**
 *
 * @author nikola
 */
public class Logs extends Controller {

    public static Result all() {
        return ok(Json.toJson(Log.find.all()));
    }
    
    public static Result after(Long after) {
        Date timestamp = new Date(after);
        List<Log> logs = Log.find.where()
                .gt("logTime", timestamp)
                .findList();
        return ok(Json.toJson(logs));
    }
}
