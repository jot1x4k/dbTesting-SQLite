package data;
import java.util.List;
import java.util.Scanner;

public class UserConsoleUI {
    private final Scanner scanner;
    private final IUserRepository userRepository;

    public UserConsoleUI(IUserRepository userRepository) {
        this.scanner = new Scanner(System.in);
        this.userRepository = userRepository;
    }

    public void start() {
        int option;
        do {
            System.out.println("\n=== GESTIÓN DE USUARIOS (SOLID) ===");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Desactivar");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> updateUser();
                case 4 -> deactivateUser();
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (option != 5);
    }

    private void registerUser() {
        User user = new User();
        System.out.print("Usuario: "); user.setUsername(scanner.nextLine());
        System.out.print("Clave: "); user.setPassword(scanner.nextLine());
        System.out.print("Nombre Completo: "); user.setFullName(scanner.nextLine());
        System.out.print("Rol [MEDICO/AGENDADOR/ADMIN]: "); user.setRole(scanner.nextLine());

        userRepository.save(user);
    }

    private void listUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("ID\t\tUSER\t\tNOMBRE\t\t\tROL\t\tESTADO");
        System.out.println("----------------------------------------------------------------");
        for (User u : users) {
            System.out.println(u);
        }
    }

    private void updateUser() {
        System.out.print("Ingrese ID del usuario a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("Deje en blanco para mantener el valor actual.");

        System.out.print("Nuevo Usuario (" + existingUser.getUsername() + "): ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) existingUser.setUsername(input);

        System.out.print("Nueva Clave: ");
        input = scanner.nextLine();
        if (!input.isEmpty()) existingUser.setPassword(input);

        System.out.print("Nuevo Nombre (" + existingUser.getFullName() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) existingUser.setFullName(input);

        System.out.print("Nuevo Rol (" + existingUser.getRole() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) existingUser.setRole(input);

        userRepository.update(existingUser);
    }

    private void deactivateUser() {
        System.out.print("ID a desactivar: ");
        int id = Integer.parseInt(scanner.nextLine());
        userRepository.deactivate(id);
    }
}
