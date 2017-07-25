package io.robusta.demo;

import java.lang.reflect.Field;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import io.robusta.ForaDataSource;
import io.robusta.business.UserBusiness;
import io.robusta.domain.User;

@Path("users")
// @Produces(MediaType.APPLICATION_JSON)
public class UserDemoResource {

    /**
     * Simply fetch all users
     */

    @GET
    @Produces("application/json")
    public List<User> findUsers(@Context HttpServletResponse resp) throws Exception {

   
        return ForaDataSource.getInstance().getUsers();
    }

    /**
     * First try with id:1 which is an Admin
     * Then try with id:4 which is not; What is the difference
     * between User and Admin ?
     *
     * @param id: userId
     * @return User
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("id") Long id) {
        User user = new UserBusiness().findById(id);
        return user;
    }

    /**
     * Dirty on screen, isn't it ? Why ?
     * <p>
     * The difficulty here is that we return Users, not Admins
     * We don't have the statement attributes. Check back getUsers(),
     * you'll see we don't have neither statements.
     * We'll see later how to handle this problem
     *
     * @param isAdmin
     * @return
     */
    @GET
    @Path("admins")
    public List<User> findAdmins(@DefaultValue("true") @QueryParam("admin") boolean isAdmin) {

        return ForaDataSource.getInstance().getUsers().stream()
                .filter(u -> u.isAdmin())
                .collect(Collectors.toList());

    }



	@POST
	// application/x-www-form-urlencoded
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	@Path("web1.0")
	public User createUser(@FormParam("admin") boolean isAdmin,
			@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("male") boolean male) {
		return new UserBusiness().createUser(email, name, isAdmin, male);
	}

    @POST
    // application/json
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("web2.0")
    public User createUser(User user) {
        return new UserBusiness().createUser(
                user.getEmail(), user.getName(),
                user.isAdmin(),
                user.isMale());
    }

    // http://localhost:8080/demo/users/Raj/admin
    @GET
    @Path("{name}/{property}")
    public String getByProperty(@PathParam("name") String name,
                                @PathParam("property") String property)
            throws IllegalAccessException, SecurityException {
        User u = new UserBusiness().findByName(name);
        if (u == null) {
            throw new NotFoundException("Can't find user with name: " + name);
        }

        try {
            Field f = User.class.getDeclaredField(property);
            f.setAccessible(true);
            return f.get(u).toString();
        } catch (NoSuchFieldException e) {
            throw new NotFoundException("can't find property " + property);
        }


    }

}
