package com.rahul.kafka.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Object2Bytes {
    public static byte[] object2Bytes(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();

            return bos.toByteArray();
        } catch (IOException ex) {

        } finally {
            try {
                if (oos != null)
                    oos.close();

                if (bos != null)
                    bos.close();
            } catch (IOException ex) {

            }
        }

        return null;
    }
}