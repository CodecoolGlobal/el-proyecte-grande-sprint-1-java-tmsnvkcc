import { useEffect } from 'react';
import './Categories.styles.css';
import { iconLibraryConfig, axiosConfigWithAuth } from '@src/config';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import getProfileData from '@src/pages/Profile/Profile.page.hooks';

const Categories = () => {
  const { data, isDataLoading, isDataError, refetch } = getProfileData('get-categories');

  useEffect(() => {
    refetch();
  }, []);

  const handleDeleteCategory = async (categoryId) => {
    await axiosConfigWithAuth({
      method: 'DELETE',
      url: '/api/users/delete-category',
      data:JSON.stringify(categoryId),
    });
    refetch();
  };

  return (
    <>
      <div className='profile-categories'>
        <table className={'rwd-table'}>
          <thead>
            <tr>
              <th>Category name</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {isDataLoading ? (
              <tr>
                <td className={'table-loading'}>
                  <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
                </td>
              </tr>
              ) : isDataError ? (
                <p>Error fetching data</p>
              ) : (
               data.map((category) => {
                return (
                  <tr key={category.id}>
                    <td>{category.name}</td>
                    <td><button onClick={() => handleDeleteCategory(category.id)}>Delete</button></td>
                  </tr>
                );
              })
            )}
          </tbody>
        </table>

      </div>
    </>
  );
};

export default Categories;
