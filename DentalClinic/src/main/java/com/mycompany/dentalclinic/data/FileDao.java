package com.mycompany.dentalclinic.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public abstract class FileDao<T> {

    // change !final give it getter and setter 
    private String FILE_PATH;
    private final int columnCount;
    private final boolean hasHeaders;

    public FileDao(String FILE_PATH, int columnCount, boolean hasHeaders) {
        this.FILE_PATH = FILE_PATH;
        this.columnCount = columnCount;
        this.hasHeaders = hasHeaders;
    }
// call 

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public void setFILE_PATH(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    public List<T> read(Function<String[], T> mapper) throws DataException {

        ArrayList<T> result = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            if (hasHeaders) { // throw out header...
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                if (tokens.length == columnCount) {
                    T obj = mapper.apply(tokens);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
        return result;
    }

    public void write(Collection<T> items, Function<T, String> mapper, String header) throws DataException {
        try ( PrintWriter writer = new PrintWriter(FILE_PATH)) {
            if (this.hasHeaders) {
                writer.println(header);
            }
            for (T obj : items) {
                if (obj != null) {
                    writer.println(mapper.apply(obj));
                }
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

    public void append(T item, Function<T, String> mapper) throws DataException {
        try ( PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(mapper.apply(item));
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

}
