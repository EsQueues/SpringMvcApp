package kz.sayat.dao;

import kz.sayat.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Person> index()  {
            List<Person> people=new ArrayList<>();

      return jdbcTemplate.query("SELECT *FROM Person",
              new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT *FROM Person WHERE id=?",
                        new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name,age,email) VALUES( ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatePerson.getName(),updatePerson.getAge(),updatePerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }

/////////////////////////////////////////////
    ///////////////////////////////testing
////////////////////////////////////////////
//    public void testBatchUpdate() {
//        List<Person> people = create1000people();
//        long before=System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1,people.get(i).getId());
//                ps.setString(2,people.get(i).getName());
//                ps.setInt(3,people.get(i).getAge());
//                ps.setString(4,people.get(i).getMail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//        long after=System.currentTimeMillis();
//        System.out.println("Time:"+(after-before));
//    }
//
//    public void testMultipleUpdate() {
//        List<Person>people=create1000people();
//
//        long before=System.currentTimeMillis();
//
//        for(Person person:people){
//            jdbcTemplate.update("INSERT INTO Person VALUES (?,?,?,?)",person.getId(),person.getName(),person.getAge(),person.getMail());
//        }
//
//        long after=System.currentTimeMillis();
//        System.out.println("Time:"+(after-before));
//    }
//
//    public List<Person> create1000people(){
//        List<Person>people=new ArrayList<>();
//        for(int i=0;i<1000;i++){
//            people.add(new Person(i,"Name"+i,30,"test"+i+"@mail.ru"));
//        }
//        return people;
//    }
}