import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom';
import {
  ErrorPage,
  Home, Track,
} from 'pages/';
import { Layout } from 'components/layout';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCircleExclamation, faCircleNotch } from '@fortawesome/free-solid-svg-icons';
library.add(faCircleExclamation, faCircleNotch);

const router = createBrowserRouter(createRoutesFromElements(
  <Route errorElement={<ErrorPage />}>
    <Route path={'/'}>
      <Route index element={<Home />} />
    </Route>
    <Route path={'/profile'} element={<Layout />}>
      <Route index />
    </Route>
    <Route path={'/track'} element={<Layout />}>
      <Route index element={<Track />}/>
    </Route>
  </Route>,
));

const Application = () => {
  return (
    <RouterProvider router={router} />
  );
};

export default Application;
