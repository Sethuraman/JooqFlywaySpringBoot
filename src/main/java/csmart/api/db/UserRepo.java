package csmart.api.db;

import csmart.api.model.User;
import csmart.db.gen.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static csmart.db.gen.tables.Users.USERS;

/**
 * Created by sethur on 1/10/2016.
 */
@Repository
@Transactional
public class UserRepo {

    @Autowired
    private DSLContext dsl;

    public void createUser(User user){
        dsl.insertInto(USERS)
            .columns(USERS.EMAILID, USERS.PASSWORD_HASH, USERS.ADDRESS, USERS.PRACTICE_NAME, USERS.PRIMARY_USER)
            .values(user.getEmailid(),
                    user.getPassword(),
                    user.getAddress().toJsonNode(),
                    user.getPracticeName(),
                    user.isPrimaryUser())
            .execute();
    }

    public User getUser(String email){
        UsersRecord user =
            dsl.select()
                .from(USERS)
                .where(USERS.EMAILID.eq(email))
            .fetchOne()
                .into(UsersRecord.class);

        return new User(user);
    }

}
