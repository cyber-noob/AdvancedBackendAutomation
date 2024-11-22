package skibidi.bop;

import skibidi.bop.api.create_hardware.CreateHardware;
import skibidi.bop.api.create_hardware.response.CreateHardwareResponse;
import skibidi.bop.api.fetch_hardware.FetchHardwareById;
import skibidi.bop.api.fetch_hardware.response.FetchHardwareByIdResponse;
import skibidi.bop.core.Config;
import skibidi.bop.core.Request;
import skibidi.bop.core.RequestProcessor;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Queue<Request> queue = new LinkedList<>();
        queue.add(new CreateHardware());
        queue.add(new FetchHardwareById());
        queue.add(new FetchHardwareById());

        CreateHardwareResponse response = new RequestProcessor().process(queue)
                .body()
                .as(CreateHardwareResponse.class);


        System.out.println("id: " + response.getId());
    }
}