package controllers;

import javax.persistence.PersistenceException;
import models.City;
import play.libs.Json;
import play.mvc.*;

public class Cities extends Controller {

    public static Result all() {
        return ok(Json.toJson(City.find.all()));
    }

    public static Result byId(Long id) {
        try {
            return ok(Json.toJson(City.find.byId(id)));
        } catch (NullPointerException ex) {
            return badRequest(Json.toJson("City doesn't exist"));
        }
    }

    public static Result byName(String name) {
        try {
            City city = City.find.where()
                    .eq("name", name)
                    .findUnique();
            return ok(Json.toJson(city));
        } catch (NullPointerException ex) {
            return badRequest(Json.toJson("City doesn't exist"));
        }
    }

    public static Result save() {
        City city = Json.fromJson(request().body().asJson(), City.class);
        try {
            city.save();
            return ok(Json.toJson("Ok"));
        } catch (PersistenceException ex) {
            return badRequest(Json.toJson("Bad city name"));
        }
    }
}
