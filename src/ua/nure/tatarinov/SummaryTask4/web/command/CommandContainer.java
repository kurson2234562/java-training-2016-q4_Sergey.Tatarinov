package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Storage all commands
 */
public class CommandContainer {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("loginCommand", new LoginCommand());
        commands.put("logoutCommand", new LogoutCommand());
        commands.put("languageCommand", new LanguageCommand());
        commands.put("selectThemeCommand", new SelectThemeCommand());
        commands.put("courseCommand", new CourseCommand());
        commands.put("selectStudentCommand", new SelectStudentCommand());
        commands.put("updateJournalCommand", new UpdateJournalCommand());
        commands.put("lockCommand", new LockCommand());
        commands.put("deleteCourseCommand", new DeleteCourseCommand());
        commands.put("updateCourseButtonCommand", new UpdateCourseButtonCommand());
        commands.put("updateCourseCommand", new UpdateCourseCommand());
        commands.put("createCourseCommand", new CreateCourseCommand());
        commands.put("createLecturerCommand", new CreateLecturerCommand());
        commands.put("forgetPasswordCommand", new ForgetPasswordCommand());
        commands.put("insertInJournalCommand", new InsertInJournalCommand());
        commands.put("forgetCommand", new ForgetCommand());
        commands.put("searchCommand", new SearchCommand());
        commands.put("registerOnCourseCommand", new RegisterOnCourseCommand());
        commands.put("loadInformationCommand", new LoadInformationCommand());
        commands.put("editInformationCommand", new EditInformationCommand());
        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}


