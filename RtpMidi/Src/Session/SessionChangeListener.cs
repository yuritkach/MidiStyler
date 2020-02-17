namespace rtpmidi.session
{

    public interface ISessionChangeListener
    {
        void OnMaxNumberOfSessionsChange(int maxNumberOfSessions);
    }
}