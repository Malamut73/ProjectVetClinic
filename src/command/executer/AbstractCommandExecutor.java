package command.executer;

import repository.*;
import repository.impl.*;


public abstract class AbstractCommandExecutor implements CommandExecutor {


    protected final UserRepository userRepository = UserRepositoryImpl.GET_USER_REPOSITORY_SQL();
    protected final AppointmentRepository appointmentRepository = AppointmentRepositoryImpl.GET_APPOINTMENT_REPOSITORY_SQL();
    protected final FolderRepository folderRepository = FolderRepositoryImpl.GET_FOLDER_REPOSITORY();
    protected final NoteRepository noteRepository = NoteRepositoryImpl.GET_NOTE_REPOSITORY();

    protected AbstractCommandExecutor(){
    }

}
