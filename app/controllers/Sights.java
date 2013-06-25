package controllers;

import javax.persistence.PersistenceException;
import models.City;
import models.Sight;
import play.libs.Json;
import play.mvc.*;

/**
 *
 * @author nikola
 */
public class Sights extends Controller {

    public static Result all() {
        return ok(Json.toJson(Sight.find.all()));
    }

    public static Result byId(Long id) {
        try {
            return ok(Json.toJson(Sight.find.byId(id)));
        } catch (NullPointerException ex) {
            return badRequest(Json.toJson("Sight doesn't exist"));
        }
    }

    public static Result byName(String cityName, String name) {
        try {
            City city = City.find.where()
                    .eq("name", cityName)
                    .findUnique();
            Sight sight = Sight.find.where()
                    .eq("name", name)
                    .eq("city", city)
                    .findUnique();
            return ok(Json.toJson(sight));
        } catch (NullPointerException ex) {
            return badRequest(Json.toJson("Sight doesn't exist"));
        }
    }

    public static Result forCity(String cityName) {
        City city = City.find.where()
                .eq("name", cityName)
                .findUnique();
        return ok(Json.toJson(Sight.find.where().eq("city", city).findList()));
    }

    public static Result save() {
        Sight sight = Json.fromJson(request().body().asJson(), Sight.class);
        try {
            sight.save();
            return ok(Json.toJson(sight.getId()));
        } catch (PersistenceException ex) {
            return badRequest(Json.toJson("Error"));
        }
    }
}
