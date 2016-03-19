/*
 * Copyright 2016 Bridje Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bridje.orm.impl;

/**
 *
 */
public class EntityContextTest
{
    /*
    @Test
    public void testClassWriter() throws IOException
    {
        File f = new File("./target/MyClass.java");
        if(f.exists())
        {
            f.delete();
        }
        f.createNewFile();
        Writer w = new FileWriter(f);
        ClassWriter cw = new ClassWriter(w);
        cw.classPackage("some.package");
                
        cw.importClass("some.package.SomeClass");
        cw.importClass("some.package.SomeClass2");
        cw.importClass("some.package.SomeInterf");
        cw.importClass("some.package.SomeInterf2");

        cw.publicAccess().classDec("MyClass")
                .extendsFrom("SomeClass").extendsFrom("SomeClass2")
                .implementsInterf("SomeInterf").implementsInterf("SomerInterf2");
        cw.begin();
        
        cw.privateAccess().finalElement().staticElement().fieldDec("Integer", "myField", "0");
        
        cw.publicAccess().contructorDec("MyClass").methodParam("String", "name").methodParam("int", "someInt");
        cw.begin();
            cw.codeLine("super()");
        cw.end();
        
        cw.end();
        w.flush();
    }
    */

    /**
     * Test of find method, of class EntityContext.
     */
    //@Test
    public void testFind()
    {
        /*
        JdbcService jdbcServ = Ioc.context().find(JdbcService.class);
        DataSourceConfig config = new DataSourceConfig();
        config.setDriver("com.mysql.jdbc.Driver");
        config.setUrl("jdbc:mysql://localhost:3306/testdb");
        config.setUser("root");
        config.setName("TestDB");
        config.setPassword("asd");
        DataSource ds = jdbcServ.createDataSource(config);
        EntityContext instance = new EntityContextImpl(ds);

        Group group = instance.find(Group.class, 1l);
        
        User user = new User(1l, "Admin");
        user.setAge((short)30);
        user.setBrithday(new Date());
        user.setClasif('A');
        user.setCounts((byte)1);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setCredit(50.5f);
        user.setEnable(true);
        user.setHour(new Time(System.currentTimeMillis()));
        user.setMins(10);
        user.setMoney(100.40d);
        user.setUpdated(new java.sql.Date(System.currentTimeMillis()));
        user.setYear(2016l);
        user.setGroup(group);
        //instance.insert(user);
        
        //instance.fixTable(User.class);
        //instance.fixTable(Group.class);
        System.out.println(instance.find(User.class, 1l).getGroup().getName());
        
        instance.insert(new User(3l, "Other Admin"));
        
        instance.query(User.class)
                //.by(User_.name.eq("Admin"))
                .fetchAll().stream()
                .map((user) -> user.getName())
                .forEach(System.out::println);
        
        /*
        User user = instance.find(User.class, 1l);
        instance.delete(user);
        user = instance.find(User.class, 1l);
        assertNull(user);

        instance.insert(new User(1l, "Admin"));
        User user = instance.find(User.class, 1l);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertEquals(user.getId().longValue(), 1l);
        assertEquals(user.getName(), "Admin");

        instance.insert(new User(2l, "Admin 1"));
        user = instance.find(User.class, 2l);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertEquals(user.getId().longValue(), 2l);
        assertEquals(user.getName(), "Admin 1");
        */
    }
}