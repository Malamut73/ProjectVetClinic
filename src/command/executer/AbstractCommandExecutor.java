package command.executer;

import repository.AppointmentRepository;
import repository.ClientRepository;
import repository.StaffRepository;
import repository.impl.AppointmentRepositoryImpl;
import repository.impl.ClientRepositoryImpl;
import repository.impl.StaffRepositoryImpl;

public abstract class AbstractCommandExecutor implements CommandExecutor {


    protected final ClientRepository clientRepository = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL();
    protected final StaffRepository staffRepository = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL();
    protected final AppointmentRepository appointmentRepository = AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL();

    protected AbstractCommandExecutor(){
    }
    }
