package com.txt.mongoredis;

import java.util.List;

import com.txt.mongoredis.repositories.mongodb.dbsecond.PlayerRepository;
import com.txt.mongoredis.entities.mongodb.dbsecond.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class DemoCRUD {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoCRUD.class, args);
        PlayerRepository playerRepository = context.getBean(PlayerRepository.class);

        DemoCreate(playerRepository);
        DemoRead(playerRepository);
        DemoUpdate(playerRepository);
        DemoDelete(playerRepository);
    }

    private static void DemoDelete(PlayerRepository playerRepository) {
        System.out.println("--------------- FindAll -----------------");
        List<Player> allPlayers = playerRepository.findAll();
        for (Player player : allPlayers) {
            System.out.println(player);
        }

        System.out.println("--------------- Delete -----------------");
        Player result = playerRepository.findById("1").orElse(null);
        if (result == null) {
            System.out.println("Not found player with id = 1");
        } else {
            playerRepository.delete(result);
            System.out.println("Deleted!");
        }

        System.out.println("--------------- FindAll After Delete -----------------");
        allPlayers = playerRepository.findAll();
        for (Player player : allPlayers) {
            System.out.println(player);
        }
    }

    private static void DemoUpdate(PlayerRepository playerRepository) {
        System.out.println("--------------- FindAll -----------------");
        List<Player> allPlayers = playerRepository.findAll();
        for (Player player : allPlayers) {
            System.out.println(player);
        }

        System.out.println("--------------- Update -----------------");
        Player result = playerRepository.findById("1").orElse(null);
        if (result == null) {
            System.out.println("Not found player with id = 1");
        } else {
            result.setName("Marcelo");
            playerRepository.save(result);
            System.out.println("Updated!");
        }

        System.out.println("--------------- FindAll After Update -----------------");
        allPlayers = playerRepository.findAll();
        for (Player player : allPlayers) {
            System.out.println(player);
        }
    }

    private static void DemoRead(PlayerRepository playerRepository) {
        System.out.println("--------------- Find player with name = 'Smith' -----------------");
        List<Player> list1 = playerRepository.findByName("Smith");
        for (Player player : list1) {
            System.out.println(player);
        }

        System.out.println("--------------- Find player with name contains 'S' -----------------");
        List<Player> list2 = playerRepository.findByNameLike("S");
        for (Player player : list2) {
            System.out.println(player);
        }
    }

    private static void DemoCreate(PlayerRepository playerRepository) {
        System.out.println("--------------- Insert -----------------");
        Player player1 = new Player();
        player1.setName("Smith C");
        player1.setAge(33);
        player1.setFootballClub("Juventus");
        player1.setPosition("Striker");
        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setId("1");
        player2.setName("Smith D");
        player2.setAge(19);
        player2.setFootballClub("Real Madrid");
        player2.setPosition("Midfielder");
        playerRepository.save(player2);
        System.out.println("Insert Success!");

        System.out.println("--------------- FindAll -----------------");
        List<Player> allPlayers = playerRepository.findAll();
        for (Player player : allPlayers) {
            System.out.println(player);
        }
    }
}
