namespace rtipmidi.session
{

    public interface ISessionChangeListener
    {
        void OnMaxNumberOfSessionsChange(int maxNumberOfSessions);
    }
}