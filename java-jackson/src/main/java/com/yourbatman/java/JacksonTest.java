package com.yourbatman.java;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jackson框架
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年03月11日 下午 2:48
 */
public class JacksonTest {

    @Getter
    @Setter
    static String json;

    {
        json = "{\"EBusinessID\":\"1109259\",\"Count\":null,\"PushTime\":\"2015-03-06 21:16:58\",\"Data\":[{\"EBusinessID\":\"1109259\",\"OrderCode\":\"\",\"ShipperCode\":\"EMS\",\"LogisticCode\":\"5042260908504\",\"Success\":\"true\",\"Reason\":\"\",\"State\":\"2\",\"CallBack\":\"0\",\"Traces\":[{\"AcceptTime\":\"2015-03-06 21:16:58\",\"AcceptStation\":\"深圳市横岗速递营销部已收件，（揽投员姓名：钟定基;联系电话：）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-07 14:25:00\",\"AcceptStation\":\"离开深圳市 发往广州市\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-08 00:17:00\",\"AcceptStation\":\"到达广东速递物流公司广航中心处理中心（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-08 01:15:00\",\"AcceptStation\":\"离开广州市 发往北京市（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-09 09:01:00\",\"AcceptStation\":\"到达北京黄村转运站处理中心（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-09 18:39:00\",\"AcceptStation\":\"离开北京市 发往呼和浩特市（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-10 18:06:00\",\"AcceptStation\":\"到达  呼和浩特市 处理中心\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-11 09:53:48\",\"AcceptStation\":\"呼和浩特市邮政速递物流分公司金川揽投部安排投递（投递员姓名：安长虹;联系电话：18047140142）\",\"Remark\":\"\"}],\"OrderState\":null,\"AccountName\":null,\"AccountTel\":null,\"AccountNum\":null},{\"EBusinessID\":\"1109259\",\"OrderCode\":\"\",\"ShipperCode\":\"EMS\",\"LogisticCode\":\"5042260943004\",\"Success\":\"true\",\"Reason\":\"\",\"State\":\"2\",\"CallBack\":\"0\",\"Traces\":[{\"AcceptTime\":\"2015-03-07 15:26:09\",\"AcceptStation\":\"深圳市横岗速递营销部已收件，（揽投员姓名：周宏彪;联系电话：13689537568）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-08 16:32:00\",\"AcceptStation\":\"离开深圳市 发往广州市\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-09 00:58:00\",\"AcceptStation\":\"到达广东速递物流公司广航中心处理中心（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-09 01:15:00\",\"AcceptStation\":\"离开广州市 发往北京市（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-10 05:20:00\",\"AcceptStation\":\"到达北京黄村转运站处理中心（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-10 11:59:00\",\"AcceptStation\":\"离开北京市 发往廊坊市（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-10 14:23:00\",\"AcceptStation\":\"到达廊坊市处理中心（经转）\",\"Remark\":\"\"},{\"AcceptTime\":\"2015-03-11 08:55:00\",\"AcceptStation\":\"离开廊坊市 发往保定市（经转）\",\"Remark\":\"\"}],\"OrderState\":null,\"AccountName\":null,\"AccountTel\":null,\"AccountNum\":null}]}";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String test = URLEncoder.encode("的士大夫sdf", "GBK");
        System.out.println();
    }
    @Test
    public void ForDebug() throws IOException {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //反序列化
        ReceiveData deserial = objectMapper.readValue(json, ReceiveData.class);
        //序列化
        String serial = objectMapper.writeValueAsString(deserial);
        boolean b = serial.contentEquals(json);
    }

    //ObjectMapper Java对象转为JSON字符串
    @Test
    public void test1() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());
        //2.创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        /*

            转换方法：
                writeValue(参数1，obj):
                    参数1：
                        File：将obj对象转换为JSON字符串，并保存到指定的文件中
                        Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
                        OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
                writeValueAsString(obj):将对象转为json字符串

         */
        String json = mapper.writeValueAsString(p);
        json = mapper.writeValueAsString(p);
        mapper.writeValue(new FileWriter("d://b.txt"), p);
    }

    //ObjectMapper 数组转json
    @Test
    public void test3() throws Exception {
        //1.创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());

        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(23);
        p1.setGender("男");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(23);
        p2.setGender("男");
        p2.setBirthday(new Date());


        //创建List集合
        List<Person> ps = new ArrayList<Person>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);


        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ps);
        // [{},{},{}]
        //[{"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"},{"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"},{"name":"张三","age":23,"gender":"男","birthday":"2018-07-07"}]
        System.out.println(json);
    }

    //ObjectMapper map转json
    @Test
    public void test4() throws Exception {
        //1.创建map对象
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");


        //2.转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        //{"name":"张三","age":23,"gender":"男"}
        System.out.println(json);//{"gender":"男","name":"张三","age":23}
    }

    //ObjectMapper 演示 JSON字符串转为Java对象
    @Test
    public void test5() throws Exception {
        //1.初始化JSON字符串
        String json = "{\"gender1\":\"男\",\"gender\":\"男\",\"name\":\"张三\",\"age\":\"23\"}";//字符串也可以转化为数字

        //2.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();
        //3.转换为Java对象 Person对象
        Person person = mapper.readValue(json, Person.class);

        System.out.println(person);
    }

    //ObjectMapper，序列化
    @Test //
    public void Bean2JsonStr() throws ParseException, JsonGenerationException, JsonMappingException, IOException {
        // 使用ObjectMapper转化对象为Json
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);   //设置日期序列化格式

        City city1 = new City();
        city1.setId(1);
        city1.setCityName("gz");
        City city2 = new City();
        city2.setId(2);
        city2.setCityName("dg");

        Province province = new Province();
        province.setId(1);
        province.setName("GD");
        province.setBirthDate(new Date());
        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);
        province.setCities(cities);

        Country country = new Country();
        country.setCountryName("China");
        country.setId(1);
        country.setEstablishTime(dateFormat.parse("1949-10-01"));
        country.setLakes(new String[]{"Qinghai Lake", "Poyang Lake", "Dongting Lake", "Taihu Lake"});
        HashMap<String, String> forest = new HashMap<String, String>();
        forest.put("no.1", "dxal");
        forest.put("no.2", "xxal");
        country.setForest(forest);
        List<Province> provinces = new ArrayList<Province>();
        provinces.add(province);
        country.setProvinces(provinces);


        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);     // 为了使JSON视觉上的可读性，在生产中不需如此，会增大Json的内容
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);  // 配置mapper忽略空属性
        mapper.writeValue(new File("country.json"), country);  // 默认情况，Jackson使用Java属性字段名称作为 Json的属性名称,也可以使用Jackson annotations(注解)改变Json属性名称
    }

    //ObjectMapper，反序列化
    @Test //
    public void JsonStr2Bean() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("country.json");
        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Country country = mapper.readValue(jsonFile, Country.class);
        System.out.println(country.getCountryName() + country.getEstablishTime());
        List<Province> provinces = country.getProvinces();
        for (Province province : provinces) {
            System.out.println("province:" + province.getName() + "\n" + "birthDate:" + province.getBirthDate());
            for (City city : province.getCities()) {
                System.out.println(city.getId() + " " + city.getCityName());
            }
        }
    }

    //ObjectMapper，序列化数组
    @Test
    public void JsonStr2List() throws IOException {
        City city1 = new City();
        city1.setId(1);
        city1.setCityName("gz");
        City city2 = new City();
        city2.setId(2);
        city2.setCityName("dg");

        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);

        ObjectMapper mapper = new ObjectMapper();
        String listJsonStr = mapper.writeValueAsString(cities);
        System.out.println(listJsonStr);
        List<City> list = mapper.readValue(listJsonStr, new TypeReference<List<City>>() {

        });
        for (City city : list) {
            System.out.println("id:" + city.getId() + " cityName:" + city.getCityName());
        }

    }

    //stream api之 Jackson Generator 序列化简单对象
    @Test
    public void writeSimpleToFile() {
        //Jackson Generator用于生成JSON。对于简单的变量这种数据类型，Jackson Generator和Jackson JsonParser一样从JsonFactory中创建。
        JsonFactory jsonFactory = new JsonFactory();
        OutputStream outputStream;
        // Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[]{"engine", "brake"});

        try {
            outputStream = new FileOutputStream("simple.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            System.out.println();
            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);
            generator.writeObjectFieldStart("owner");
            generator.writeStringField("first", "Gatsby");
            generator.writeStringField("last", "Newton");
            generator.writeEndObject();

            generator.writeArrayFieldStart("component");
            generator.writeString("engine");
            generator.writeString("brake");
            generator.writeEndArray();

            // Write a object.
            // generator.writeObjectField("owner", new Car.Owner("Gatsby", "Newton"));
            // generator.writeObjectField("component", new String[]{"engine", "brake"});

            generator.writeEndObject();

            generator.flush();
            generator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // stream api 之 Jackson Generator 序列化复杂对象
    @Test
    public void writeComplexToFile() {
        // 但是对于复杂的数据类型，Jackson Generator必须从ObjectMapper.getJsonFactory()的JsonFactory中创建，否则会报一下错误：
        // 其中，ObjectMapper.getJsonFactory()方法被标记为deprecated，不过这并没有关系，这种情况在Java编程中经常遇到，这是可以用的。但是对于那种建议不要使用的方法，最好弃之不用。

        // If you use JsonFactory jsonFactory = new JsonFactory(), it shows the error:
        // java.lang.IllegalStateException: No ObjectCodec defined for the generator,
        // can only serialize simple wrapper types (type passed edu.wzm.jackson.Car$Owner)
        JsonFactory jsonFactory = new ObjectMapper().getJsonFactory();

        OutputStream outputStream = null;
        // Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[]{"engine", "brake"});

        try {
            outputStream = new FileOutputStream("complex.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);

            // Write a object.
            generator.writeObjectField("owner", new Car.Owner("Gatsby", "Newton"));
            generator.writeObjectField("component", new String[]{"engine", "brake"});

            generator.writeEndObject();

            generator.flush();
            generator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // stream api 之 JsonParser 反序列化json数据
    @Test
    public void readFromFile() {
        String json = "{\n" +
                "  \"name\": {\n" +
                "    \"first\": \"Joe\",\n" +
                "    \"last\": \"Sixpack\"\n" +
                "  },\n" +
                "  \"gender\": \"MALE\",\n" +
                "  \"verified\": false,\n" +
                "  \"userImage\": \"Rm9vYm\"\n" +
                "}";
        JsonFactory jsonFactory = new JsonFactory();
        String temp;
        JsonToken jsonToken;
        String fieldName;
        String field;

        try {
            //createParser有很多种重载，具体可以看API
            JsonParser parser = jsonFactory.createParser(json);
            //进入JSON的“{”，获取下一个值的所属token，还可以获取这里明确第一个是{，直接跳过
            jsonToken = parser.nextToken();
            while ((jsonToken = parser.nextToken()) != JsonToken.END_OBJECT) {
                fieldName = parser.getCurrentName();
                temp = parser.getText();
                temp = parser.getValueAsString();

                jsonToken = parser.nextToken();
                temp = parser.getCurrentName();
                temp = parser.getText();
                temp = parser.getValueAsString();
                if (fieldName.equals("name")) {
                    System.out.println(fieldName + ":");
                    while ((jsonToken = parser.nextToken()) != JsonToken.END_OBJECT) {
                        temp = parser.getCurrentName();
                        temp = parser.getText();
                        temp = parser.getValueAsString();

                        jsonToken = parser.nextToken();
                        temp = parser.getText();
                        temp = parser.getValueAsString();
                        field = parser.getCurrentName();

                        System.out.println("\t" + field + ": " + (temp = parser.getValueAsString()));
                    }
                } else {
                    System.out.println(fieldName + ": " + (temp = parser.getText()));
                }
            }
            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //stream api和ObjectMapper之间的桥梁，使用自定义JsonSerializer、JsonDeSerializer
    @Test
    public void StreamJsonStr2List() throws IOException {
        //定义pojo
        City city1 = new City();
        //city1.setId(1);
        city1.setCityName("gz");
        City city2 = new City();
        city2.setId(2);
        city2.setCityName("dg");

        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);

        Province province = new Province();
        province.setId(1);
        province.setName("GD");
        province.setBirthDate(new Date());
        province.setCities(cities);

        Country country = new Country();
        country.setCountryName("China");
        country.setId(1);
        country.setLakes(new String[]{"Qinghai Lake", "Poyang Lake", "Dongting Lake", "Taihu Lake"});
        HashMap<String, String> forest = new HashMap<String, String>();
        forest.put("no.1", "dxal");
        forest.put("no.2", "xxal");
        country.setForest(forest);
        List<Province> provinces = new ArrayList<Province>();
        provinces.add(province);
        country.setProvinces(provinces);

        //注册自定义serializer和deserializer，使用stream api
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(City.class, new CityJsonSerializer());
        mapper.registerModule(module);
        String listJsonStr = mapper.writeValueAsString(country);

        System.out.println(listJsonStr);

        ObjectMapper mapper2 = new ObjectMapper();
        SimpleModule module2 = new SimpleModule();
        //focus 这里会报错，想想为什么
        module2.addDeserializer(List.class, new CityJsonDeSerializer());
        mapper2.registerModule(module2);
        // List<City> list = mapper2.readValue(listJsonStr, new  TypeReference<List<City>>(){} );
        Country country1 = mapper2.readValue(listJsonStr, Country.class);
        System.out.println(country1);

        // for (City city: list) {
        //     System.out.println("id:"+city.getId()+" cityName:"+city.getCityName());
        // }

    }

    //Tree Mode生成json
    @Test
    public void TreeMode2Json() throws IOException {

        //创建一个节点工厂,为我们提供所有节点
        JsonNodeFactory factory = new JsonNodeFactory(false);
        //创建一个json factory来写tree modle为json
        JsonFactory jsonFactory = new JsonFactory();
        //创建一个json生成器
        JsonGenerator generator = jsonFactory.createGenerator(new FileWriter(new File("country2.json")));
        //注意，默认情况下对象映射器不会指定根节点，下面设根节点为country
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode country = factory.objectNode();
        country.put("id", 1);
        country.put("countryName", "China");
        country.put("establishTime", "1949-10-01");

        ArrayNode provinces = factory.arrayNode();
        ObjectNode province = factory.objectNode();
        ObjectNode city1 = factory.objectNode();
        city1.put("id", 1);
        city1.put("cityName", "gz");
        ObjectNode city2 = factory.objectNode();
        city2.put("id", 1);
        city2.put("cityName", "dg");
        ArrayNode cities = factory.arrayNode();
        cities.add(city1).add(city2);
        province.put("cities", cities);
        provinces.add(province);
        country.put("provinces", provinces);

        ArrayNode lakes = factory.arrayNode();
        lakes.add("QingHai Lake").add("Poyang Lake").add("Dongting Lake").add("Taihu Lake");
        country.put("lakes", lakes);

        ObjectNode forest = factory.objectNode();
        forest.put("no.1", "dxal");
        forest.put("no.2", "xxal");
        country.put("forest", forest);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);  // 配置mapper忽略空属性
        mapper.writeTree(generator, country);
    }

    //Tree Mode读取json
    @Test
    public void TreeModeReadJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Jackson提供一个树节点被称为"JsonNode",ObjectMapper提供方法来读json作为树的JsonNode根节点
        JsonNode node = mapper.readTree(new File("country2.json"));
        // 看看根节点的类型
        System.out.println("node JsonNodeType:" + node.getNodeType());
        System.out.println("---------获取特定节点，JsonPointer表达式----------------------");
        System.out.println(node.get("countryName"));
        System.out.println(node.at("/provinces"));
        System.out.println(node.at("/provinces/0"));
        System.out.println(node.at("/provinces/0/cities/1/cityName"));
        System.out.println(node.at("/forest/no.1"));

        System.out.println("---------得到所有node节点的子节点名称----------------------");
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.print(fieldName + " ");
        }
        System.out.println("\n---------------------------------------------------");

        JsonNode lakes = node.get("lakes");
        System.out.println("lakes:" + lakes + " JsonNodeType:" + lakes.getNodeType());
    }

    static class User {

        private String name;

        private String gender;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[User: \n")
                    .append("name = " + this.name + "\n")
                    .append("gender = " + this.gender + "\n")
                    .append("age = " + this.age + "]");
            return sb.toString();
        }
    }

    static class Car {

        private String brand;

        private int doors;

        private Owner owner;

        private String[] component;

        public Car() {
        }

        public Car(String brand, int doors, Owner owner, String[] component) {
            this.brand = brand;
            this.doors = doors;
            this.owner = owner;
            this.component = component;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getDoors() {
            return doors;
        }

        public void setDoors(int doors) {
            this.doors = doors;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public String[] getComponent() {
            return component;
        }

        public void setComponent(String[] component) {
            this.component = component;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[Car: \n")
                    .append("brand = " + this.brand + "\n")
                    .append("doors = " + this.doors + "\n")
                    .append("owner = " + this.owner + "\n")
                    .append("component = " + this.component + "]");
            return sb.toString();
        }

        public static class Owner {

            private String first;

            private String last;

            public Owner(String first, String last) {
                this.first = first;
                this.last = last;
            }

            public String getFirst() {
                return first;
            }

            public void setFirst(String first) {
                this.first = first;
            }

            public String getLast() {
                return last;
            }

            public void setLast(String last) {
                this.last = last;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("[Owner: \n")
                        .append("first = " + this.first + "\n")
                        .append("last = " + this.last + "]");
                return sb.toString();
            }
        }
    }

    static class Person {

        @JsonProperty("Name")
        private String name;

        private int age;

        private String gender;

        //@JsonIgnore // 忽略该属性
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthday;

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }

    @JsonSerialize(using = CityJsonSerializer.class)
    static class City {

        private Integer id;

        private String cityName;

        public City() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

    }

    static class Country {

        private Integer id;

        private String countryName;

        private Date establishTime;

        private List<Province> provinces;

        private String[] lakes;

        private Map<String, String> forest = new HashMap<String, String>();

        public Country() {

        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public Date getEstablishTime() {
            return establishTime;
        }

        public void setEstablishTime(Date establishTime) {
            this.establishTime = establishTime;
        }

        public List<Province> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<Province> provinces) {
            this.provinces = provinces;
        }

        public String[] getLakes() {
            return lakes;
        }

        public void setLakes(String[] lakes) {
            this.lakes = lakes;
        }

        public Map<String, String> getForest() {
            return forest;
        }

        public void setForest(Map<String, String> forest) {
            this.forest = forest;
        }

    }

    static class Province {

        private Integer id;

        private String name;

        private Date birthDate;

        private List<City> cities;

        public Province() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }
    }

    @Data
    static class ReceiveData {

        @JsonView(ReceiveData.class)
        @JsonProperty("EBusinessID")
        private String EBusinessID;

        private Class date;

        @JsonProperty("Count")
        private String count;

        @JsonProperty("PushTime")
        private Date PushTime;

        @JsonProperty("Data")
        private List<BdmPackageInformation> Data;

        @Getter
        @Setter
        public static class BdmPackageInformation {

            @JsonProperty("EBusinessID")
            private String EBusinessID;

            @JsonProperty("OrderCode")
            private String OrderCode;

            @JsonProperty("ShipperCode")
            private String ShipperCode;

            @JsonProperty("LogisticCode")
            private String LogisticCode;

            @JsonProperty("Success")
            private String Success;

            @JsonProperty("Reason")
            private String Reason;

            @JsonProperty("State")
            private String State;

            @JsonProperty("CallBack")
            private String CallBack;

            @JsonProperty("Traces")
            private List<Trace> Traces;

            //以下是货款状态才有
            @JsonProperty("OrderState")
            private String OrderState;

            @JsonProperty("AccountName")
            private String AccountName;

            @JsonProperty("AccountTel")
            private String AccountTel;

            @JsonProperty("AccountNum")
            private String AccountNum;
        }

        @Setter
        @Getter
        static class Trace {

            @JsonProperty("AcceptTime")
            private String AcceptTime;

            @JsonProperty("AcceptStation")
            private String AcceptStation;

            @JsonProperty("Remark")
            private String Remark;
        }
    }

    static class CityJsonDeSerializer extends JsonDeserializer<List<City>> {

        @Override
        public List<City> deserialize(JsonParser parser, DeserializationContext deserializationcontext) throws IOException,
                JsonProcessingException {
            List<City> list = new ArrayList<City>();
            // 开始解析数组，第一个JsonToken必须是JsonToken.START_ARRAY"["
            if (!JsonToken.START_ARRAY.equals(parser.getCurrentToken())) {
                System.out.println(parser.getCurrentToken());
                return null;
            }
            // 解析符号直到字符串结尾
            while (!parser.isClosed()) {
                // 如果有必要的话  ，这个方法会沿着流前进直到足以确下一个JsonToken的类型
                JsonToken token = parser.nextToken();
                // 如果是最后一个JsonToken，那么就结束了
                if (token == null)
                    break;
                // 数组的每个元素都是对象，因此下一个JsonToken是JsonToken.START_OBJECT"{"
                if (!JsonToken.START_OBJECT.equals(token)) {
                    break;
                }
                City city = null;
                // 输出id字段的值
                while (true) {
                    if (JsonToken.START_OBJECT.equals(token)) {
                        city = new City();
                    }
                    token = parser.nextToken();
                    if (token == null)
                        break;

                    if (JsonToken.FIELD_NAME.equals(token)) {

                        if ("id".equals(parser.getCurrentName())) {
                            token = parser.nextToken();
                            city.setId(parser.getIntValue());
                        } else if ("cityName".equals(parser.getCurrentName())) {
                            token = parser.nextToken();
                            city.setCityName(parser.getText());
                        }

                    }
                    if (JsonToken.END_OBJECT.equals(token)) {
                        list.add(city);
                    }

                }

            }
            return list;
        }
    }

    static class CityJsonSerializer extends JsonSerializer<City> {

        @Override
        public void serialize(City city, JsonGenerator jsonGenerator, SerializerProvider arg2)
                throws IOException {
            jsonGenerator.writeStartObject();
            if (city.getId() != null) {
                jsonGenerator.writeNumberField("id1", city.getId());
            }
            jsonGenerator.writeStringField("cityName", city.getCityName());
            jsonGenerator.writeEndObject();
        }

    }
}
