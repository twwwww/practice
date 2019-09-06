package com.tww.test.datastructure.fst;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;

public class FSTTest {

    public static void main(String[] args) {
        try {
            String[] inputValues = {"cat", "deep", "do", "dog", "dogs","seek"};
            long[] outputValues = {5, 7, 17, 18, 21,10};
            PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
            Builder<Long> builder = new Builder<>(FST.INPUT_TYPE.BYTE1, outputs);
            BytesRef scratchBytes;
            IntsRefBuilder intsRefBuilder = new IntsRefBuilder();
            for (int i = 0; i < inputValues.length; i++) {
                scratchBytes = new BytesRef(inputValues[i]);
                builder.add(Util.toIntsRef(scratchBytes, intsRefBuilder), outputValues[i]);
            }
            FST<Long> fst = builder.finish();
            Long value = Util.get(fst, new BytesRef("cat"));
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
