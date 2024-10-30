package org.example;

import org.example.models.Consumer;
import org.example.models.Media;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

public class Client
{
    interface MyComparator {
        int compareTo(int i1, int i2);

    }

    public static void main(String[] args)
    {
		/*
		System.out.println("Exercise 1");

		List<Integer> myIntList = new ArrayList<>(List.of(1,2,-1,0));
		System.out.println(myIntList);

		myIntList.sort((i1,i2) -> i1 < i2 ? -1 : (i1 == i2) ? 0 : 1);
		myIntList.sort(Comparator.comparingInt(i -> i));

		System.out.println(myIntList);

		System.out.println("Exercise 2");

		//myIntList.sort(Comparator.comparingInt(i -> i));
		MyComparator myComparator = (i1,i2) -> i1 < i2 ? -1 : (i1 == i2) ? 0 : 1;
		myIntList.sort(myComparator::compareTo);
		System.out.println(myIntList);


		System.out.println("Exercise 4");
		Flux.just(1,-2,-1,0,2)
				.map(i -> {
					//System.out.println(Thread.currentThread());
					return i * 2;
				})
				.filter(i -> {
					//System.out.println(Thread.currentThread());
					return i > 4;
				})
				.subscribe(i -> {
					//System.out.println(Thread.currentThread());
					System.out.println(i);
				});

		System.out.println("\nExercise 5");

		Flux<Integer> fi = Flux.just(1,2,3,4,5);
		Flux<String> fs = fi.map(i -> "Number " + i);
		fs.subscribe(System.out::println);

		Flux.just(1,2,3,4,5)
			.map(String::valueOf)
			.subscribe(i -> {
				System.out.println("--Number " + i + " with type "+i.getClass().getName());
			});

		System.out.println("\nExercise 6");
		Flux.just(1,2,3,4,5,6,7,8,9,10)
			.buffer(7,1)
			.map(l -> {
				System.out.println(l);
				float sum = 0;
				for(int x: l)
				{
					sum += x;
				}
				return sum/l.size();

			}).subscribe(System.out::println);
		System.out.println("\nExercise 7");

		Flux<Integer> fi2 = Flux.just(1,2,3,4,5,6,7,8,9,10);
		Mono<List<Integer>> mi = fi2.collectList();
		System.out.println(mi.block());*/

		/*
		System.out.println("\nExercise 8");
		Flux.just(1,2,3,4,5,6,5,4,3,2)
			.buffer(2)
			.filter(l -> l.get(0) > l.get(1))
			.flatMapIterable(Function.identity())
			.subscribe(System.out::println);
			*/
		/*
		System.out.println("\nExercise 10");
		String BASE_URL = "https://www.google.com/";
		String MY_URI = "/";
		WebClient.create(BASE_URL)
					.get()
					.uri(MY_URI)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(String.class)
					.subscribe(System.out::println);
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/



        String CONSUMER_ID = "1";
        String MEDIA_ID = "3";
        String BASE_URL = "http://localhost:8080";
        String GET_ALL_MEDIA_URI = "/media";
        String GET_MEDIA_URI = "/media/"+MEDIA_ID;
        String GET_ALL_CONSUMERS_URI = "/consumers";
        String GET_CONSUMER_URI = "/consumers/"+CONSUMER_ID;


        //***** WORKS *****
        //GET ALL MEDIA
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_MEDIA_URI)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .subscribe(System.out::println);

//        //GET ONE MEDIA
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_MEDIA_URI)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .subscribe(System.out::println);

        //GET ALL CONSUMERS
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_CONSUMERS_URI)
//                .retrieve()
//                .bodyToFlux(Consumer.class)
//                .subscribe(System.out::println);

//        //GET ONE CONSUMER
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_CONSUMER_URI)
//                .retrieve()
//                .bodyToMono(Consumer.class)
//                .subscribe(System.out::println);

          //POST ONE MEDIA
//        Media series = new Media("The Walking Dead",LocalDate.of(2010, 10, 31),"TV Show");
//
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(GET_ALL_MEDIA_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(series)
//                .retrieve()
//                .toEntity(Media.class)
//                .subscribe(System.out::println);

          //POST ONE CONSUMER
//        Consumer michael = new Consumer("Michael", 20,"Male");
//
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(GET_ALL_CONSUMERS_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(michael)
//                .retrieve()
//                .toEntity(Consumer.class)
//                .subscribe(System.out::println);
        //DELETE ONE MEDIA
//        WebClient.create(BASE_URL)
//                 .delete()
//                 .uri(GET_MEDIA_URI)
//                 .retrieve()
//                 .toEntity(Media.class)
//                 .subscribe(System.out::println);
        // DELETE ONE CONSUMER
//        WebClient.create(BASE_URL)
//                 .delete()
//                 .uri(GET_CONSUMER_URI)
//                 .retrieve()
//                 .toEntity(Consumer.class)
//                 .subscribe(System.out::println);



        // ***** DOESNT WORK ******



//







        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


















    }
}
