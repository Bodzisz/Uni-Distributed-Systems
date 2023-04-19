
package io.github.bodzisz;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.github.bodzisz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _PersonExistException_QNAME = new QName("http://service.bodzisz.github.io/", "PersonExistException");
    private static final QName _PersonNotFoundException_QNAME = new QName("http://service.bodzisz.github.io/", "PersonNotFoundException");
    private static final QName _AddPerson_QNAME = new QName("http://service.bodzisz.github.io/", "addPerson");
    private static final QName _AddPersonResponse_QNAME = new QName("http://service.bodzisz.github.io/", "addPersonResponse");
    private static final QName _CountPersons_QNAME = new QName("http://service.bodzisz.github.io/", "countPersons");
    private static final QName _CountPersonsResponse_QNAME = new QName("http://service.bodzisz.github.io/", "countPersonsResponse");
    private static final QName _DeletePerson_QNAME = new QName("http://service.bodzisz.github.io/", "deletePerson");
    private static final QName _DeletePersonResponse_QNAME = new QName("http://service.bodzisz.github.io/", "deletePersonResponse");
    private static final QName _GetAllPersons_QNAME = new QName("http://service.bodzisz.github.io/", "getAllPersons");
    private static final QName _GetAllPersonsResponse_QNAME = new QName("http://service.bodzisz.github.io/", "getAllPersonsResponse");
    private static final QName _GetPerson_QNAME = new QName("http://service.bodzisz.github.io/", "getPerson");
    private static final QName _GetPersonResponse_QNAME = new QName("http://service.bodzisz.github.io/", "getPersonResponse");
    private static final QName _ClearPersons_QNAME = new QName("http://service.bodzisz.github.io/", "clearPersons");
    private static final QName _ClearPersonsResponse_QNAME = new QName("http://service.bodzisz.github.io/", "clearPersonsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.github.bodzisz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PersonExistException }
     * 
     * @return
     *     the new instance of {@link PersonExistException }
     */
    public PersonExistException createPersonExistException() {
        return new PersonExistException();
    }

    /**
     * Create an instance of {@link PersonNotFoundException }
     * 
     * @return
     *     the new instance of {@link PersonNotFoundException }
     */
    public PersonNotFoundException createPersonNotFoundException() {
        return new PersonNotFoundException();
    }

    /**
     * Create an instance of {@link AddPerson }
     * 
     * @return
     *     the new instance of {@link AddPerson }
     */
    public AddPerson createAddPerson() {
        return new AddPerson();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     * @return
     *     the new instance of {@link AddPersonResponse }
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link CountPersons }
     * 
     * @return
     *     the new instance of {@link CountPersons }
     */
    public CountPersons createCountPersons() {
        return new CountPersons();
    }

    /**
     * Create an instance of {@link CountPersonsResponse }
     * 
     * @return
     *     the new instance of {@link CountPersonsResponse }
     */
    public CountPersonsResponse createCountPersonsResponse() {
        return new CountPersonsResponse();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     * @return
     *     the new instance of {@link DeletePerson }
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     * @return
     *     the new instance of {@link DeletePersonResponse }
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetAllPersons }
     * 
     * @return
     *     the new instance of {@link GetAllPersons }
     */
    public GetAllPersons createGetAllPersons() {
        return new GetAllPersons();
    }

    /**
     * Create an instance of {@link GetAllPersonsResponse }
     * 
     * @return
     *     the new instance of {@link GetAllPersonsResponse }
     */
    public GetAllPersonsResponse createGetAllPersonsResponse() {
        return new GetAllPersonsResponse();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     * @return
     *     the new instance of {@link GetPerson }
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     * @return
     *     the new instance of {@link GetPersonResponse }
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link ClearPersons }
     * 
     * @return
     *     the new instance of {@link ClearPersons }
     */
    public ClearPersons createClearPersons() {
        return new ClearPersons();
    }

    /**
     * Create an instance of {@link ClearPersonsResponse }
     * 
     * @return
     *     the new instance of {@link ClearPersonsResponse }
     */
    public ClearPersonsResponse createClearPersonsResponse() {
        return new ClearPersonsResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     * @return
     *     the new instance of {@link Person }
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonExistException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonExistException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "PersonExistException")
    public JAXBElement<PersonExistException> createPersonExistException(PersonExistException value) {
        return new JAXBElement<>(_PersonExistException_QNAME, PersonExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "PersonNotFoundException")
    public JAXBElement<PersonNotFoundException> createPersonNotFoundException(PersonNotFoundException value) {
        return new JAXBElement<>(_PersonNotFoundException_QNAME, PersonNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "addPerson")
    public JAXBElement<AddPerson> createAddPerson(AddPerson value) {
        return new JAXBElement<>(_AddPerson_QNAME, AddPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "addPersonResponse")
    public JAXBElement<AddPersonResponse> createAddPersonResponse(AddPersonResponse value) {
        return new JAXBElement<>(_AddPersonResponse_QNAME, AddPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CountPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "countPersons")
    public JAXBElement<CountPersons> createCountPersons(CountPersons value) {
        return new JAXBElement<>(_CountPersons_QNAME, CountPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CountPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "countPersonsResponse")
    public JAXBElement<CountPersonsResponse> createCountPersonsResponse(CountPersonsResponse value) {
        return new JAXBElement<>(_CountPersonsResponse_QNAME, CountPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "getAllPersons")
    public JAXBElement<GetAllPersons> createGetAllPersons(GetAllPersons value) {
        return new JAXBElement<>(_GetAllPersons_QNAME, GetAllPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "getAllPersonsResponse")
    public JAXBElement<GetAllPersonsResponse> createGetAllPersonsResponse(GetAllPersonsResponse value) {
        return new JAXBElement<>(_GetAllPersonsResponse_QNAME, GetAllPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "getPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "getPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ClearPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "clearPersons")
    public JAXBElement<ClearPersons> createClearPersons(ClearPersons value) {
        return new JAXBElement<>(_ClearPersons_QNAME, ClearPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClearPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ClearPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.bodzisz.github.io/", name = "clearPersonsResponse")
    public JAXBElement<ClearPersonsResponse> createClearPersonsResponse(ClearPersonsResponse value) {
        return new JAXBElement<>(_ClearPersonsResponse_QNAME, ClearPersonsResponse.class, null, value);
    }

}
